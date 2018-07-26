package tech.intom.hitfm.presentation.models

class CarouselModel {
    
    private var isHaveText: Boolean = false
    private var isHaveButton: Boolean = false
    private var isHaveRadioButton: Boolean = false
    private var isHaveGridView: Boolean = false
    private var isHaveListView: Boolean = false

    fun isHaveText(): Boolean {
        return isHaveText
    }

    fun isHaveButton(): Boolean {
        return isHaveButton
    }

    fun isHaveRadioButton(): Boolean {
        return isHaveRadioButton
    }

    fun isHaveGridView(): Boolean {
        return isHaveGridView
    }

    fun isHaveListView(): Boolean {
        return isHaveListView
    }

    fun setIsHaveText(isHaveText: Boolean) {
        this.isHaveText = isHaveText
    }

    fun setIsHaveButton(isHaveButton: Boolean) {
        this.isHaveButton = isHaveButton
    }

    fun setIsHaveRadioButton(isHaveRadioButton: Boolean) {
        this.isHaveRadioButton = isHaveRadioButton
    }

    fun setIsHaveGridView(isHaveGridView: Boolean) {
        this.isHaveGridView = isHaveGridView
    }

    fun setIsHaveLsetIstView(isHaveListView: Boolean) {
        this.isHaveListView = isHaveListView
    }
}