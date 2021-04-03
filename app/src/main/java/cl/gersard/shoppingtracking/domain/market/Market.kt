package cl.gersard.shoppingtracking.domain.market

import cl.gersard.shoppingtracking.core.SimpleItemInfo

data class Market(
    val id: Long,
    val name: String
) : SimpleItemInfo {
    override fun id(): Long = id
    override fun name(): String = name
    override fun type(): Int = SimpleItemInfo.TYPES.MARKET
}
