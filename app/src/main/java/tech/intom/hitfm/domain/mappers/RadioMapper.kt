package tech.intom.hitfm.domain.mappers

import tech.intom.hitfm.data.models.RadioItemData
import tech.intom.hitfm.data.network.responses.RadioResponse
import tech.intom.hitfm.domain.models.RadioItem

object RadioMapper {

    fun mapRadio(response: RadioResponse): List<RadioItem> {
        val resultList = ArrayList<RadioItem>()

        response.programs.forEach { resultList.add(mapRadioItem(it)) }

        return resultList
    }

    fun mapRadioItem(item: RadioItemData): RadioItem {

        return RadioItem("")
    }
}