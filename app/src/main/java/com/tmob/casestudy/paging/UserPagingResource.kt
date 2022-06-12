package com.tmob.casestudy.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tmob.casestudy.model.UserResponseItem
import com.tmob.casestudy.repo.UserRepo
import com.tmob.casestudy.usecase.UserUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import javax.inject.Inject
import kotlin.math.max

private const val STARTING_KEY = 0
private const val LOAD_DELAY_MILLIS = 3_000L
private val firstArticleCreatedTime = LocalDateTime.now()

class UserPagingResource @Inject constructor(val userUseCase:UserUseCase) : PagingSource<Int, UserResponseItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserResponseItem> {
        // Start paging with the STARTING_KEY if this is the first load
        val start = params.key ?: STARTING_KEY
        // Load as many items as hinted by params.loadSize
        val range = start.until(start +5)

        val response = userUseCase.getMenuData()
        delay(LOAD_DELAY_MILLIS)

        return LoadResult.Page(
            data = response,
            // Make sure we don't try to load items behind the STARTING_KEY
            prevKey = when (start) {
                STARTING_KEY -> null
                else ->
                    ensureValidKey(key = range.first - params.loadSize)
            },
            nextKey = range.last + 1
        )
    }
    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)
    override fun getRefreshKey(state: PagingState<Int, UserResponseItem>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val article = state.closestItemToPosition(anchorPosition) ?: return null
        return ensureValidKey(key = article.id - (state.config.pageSize / 2))
    }
}