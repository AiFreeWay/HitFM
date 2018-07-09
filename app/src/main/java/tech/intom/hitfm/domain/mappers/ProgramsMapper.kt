package tech.intom.hitfm.domain.mappers

import tech.intom.hitfm.data.models.ProgramItemData
import tech.intom.hitfm.data.network.responses.ProgramsResponse
import tech.intom.hitfm.domain.models.ProgramItem

/**
 * Created by root on 17.04.18.
 */
object ProgramsMapper {

    fun mapPrograms(programResponse: ProgramsResponse): List<ProgramItem> {
        val resultList = ArrayList<ProgramItem>()

        programResponse.programs.forEach { resultList.add(mapProgramItem(it)) }

        return resultList
    }

    fun mapProgramItem(programItemData: ProgramItemData): ProgramItem {

        return ProgramItem("")
    }
}