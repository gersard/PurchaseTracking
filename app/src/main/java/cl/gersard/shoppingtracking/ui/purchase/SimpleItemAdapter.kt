package cl.gersard.shoppingtracking.ui.purchase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import cl.gersard.shoppingtracking.R
import cl.gersard.shoppingtracking.core.SimpleItemInfo

class SimpleItemAdapter(context: Context, resource: Int, objects: List<SimpleItemInfo>, private val listener: SimpleItemListener) :
    ArrayAdapter<SimpleItemInfo>(context, resource, objects) {

    private var originalListObjects: List<SimpleItemInfo> = objects
    private var suggestedListObjects: MutableList<SimpleItemInfo> = objects.toMutableList()

    override fun getCount(): Int {
        return suggestedListObjects.size
    }

    override fun getItem(position: Int): SimpleItemInfo {
        return suggestedListObjects[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_text_simple, parent, false)
        val simpleItem = suggestedListObjects[position]
        (itemView as TextView).text = simpleItem.name()
        itemView.setOnClickListener { listener.onSimpleItemClick(simpleItem.type(), simpleItem) }
        return itemView
    }

    override fun getFilter(): Filter {
        return object : Filter() {

            override fun convertResultToString(resultValue: Any?): CharSequence {
                return (resultValue as SimpleItemInfo).name()
            }

            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                val listOfSuggestions = mutableListOf<SimpleItemInfo>()
                if (constraint != null) {
                    originalListObjects.forEach { simpleItemInfo ->
                        if (simpleItemInfo.name().startsWith(constraint, true)) listOfSuggestions.add(simpleItemInfo)
                    }
                    filterResults.values = listOfSuggestions
                    filterResults.count = listOfSuggestions.size
                }
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                suggestedListObjects.clear()
                if (results != null && results.count > 0) {
                    (results.values as List<*>).forEach { suggestedListObjects.add((it as SimpleItemInfo)) }
                    notifyDataSetChanged()
                } else {
                    suggestedListObjects.addAll(originalListObjects)
                    notifyDataSetInvalidated()
                }
            }
        }
    }

    interface SimpleItemListener {
        fun onSimpleItemClick(type: Int, simpleItemInfo: SimpleItemInfo)
    }
}