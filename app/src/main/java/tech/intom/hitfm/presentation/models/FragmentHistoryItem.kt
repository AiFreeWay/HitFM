package tech.intom.hitfm.presentation.models

class FragmentHistoryItem(val screenKey: String, val isNavItem: Boolean, val navItemId: Int) {

    companion object {
        fun createNotNavFragmentHistoryItem(screenKey: String): FragmentHistoryItem {
            return FragmentHistoryItem(screenKey, false, -1)
        }
    }
}