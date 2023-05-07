package com.example.newscenter
import androidx.annotation.StringRes



sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Home : Screen("Home", R.string.Home)
    object SourceOne : Screen("SourceOne", R.string.SourceOne)
    object SourceTwo : Screen("SourceTwo", R.string.SourceTwo)




    companion object {

        val items = listOf(

            SourceOne,
            Home,
            SourceTwo


        )
    }
}


