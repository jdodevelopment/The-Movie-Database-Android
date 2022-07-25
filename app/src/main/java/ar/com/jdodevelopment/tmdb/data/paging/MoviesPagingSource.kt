package ar.com.jdodevelopment.tmdb.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ar.com.jdodevelopment.tmdb.data.api.MoviesApi
import ar.com.jdodevelopment.tmdb.data.dto.toEntity
import ar.com.jdodevelopment.tmdb.domain.entity.Movie
import ar.com.jdodevelopment.tmdb.domain.error.ExceptionResolver
import javax.inject.Inject


class MoviesPagingSource @Inject constructor(
    private val api: MoviesApi,
    private val exceptionResolver: ExceptionResolver,
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val pageIndex = params.key ?: 1
            val page = api.getPopularMovies(pageIndex)

            val currentPageCount = page.results.size
            val itemsBefore = (pageIndex - 1) * params.loadSize
            val itemsAfter = page.totalResults - (itemsBefore + currentPageCount)
            val prevKey = if (pageIndex == 1) null else pageIndex - 1
            val nextKey = if (itemsAfter == 0) null else pageIndex + 1

            return LoadResult.Page(
                data = page.results.map { it.toEntity() },
                prevKey = prevKey,
                nextKey = nextKey,
                itemsBefore = itemsBefore,
                itemsAfter = itemsAfter,
            )
        } catch (throwable: Throwable) {
            LoadResult.Error(exceptionResolver.resolve(throwable))
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

}