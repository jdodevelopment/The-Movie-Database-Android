package ar.com.jdodevelopment.tmdb.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ar.com.jdodevelopment.tmdb.data.api.MoviesApi
import ar.com.jdodevelopment.tmdb.data.dto.toEntity
import ar.com.jdodevelopment.tmdb.domain.entity.Movie
import javax.inject.Inject


class MoviesPagingSource @Inject constructor(
    private val api: MoviesApi
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPage = params.key ?: 1
            val movieListResponse = api.getPopularMovies(nextPage)

            LoadResult.Page(
                data = movieListResponse.results.map { it.toEntity() },
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = movieListResponse.page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

}