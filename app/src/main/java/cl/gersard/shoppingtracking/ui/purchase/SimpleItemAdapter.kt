package cl.gersard.shoppingtracking.ui.purchase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import cl.gersard.shoppingtracking.R
import cl.gersard.shoppingtracking.core.SimpleItemInfo

class SimpleItemAdapter(context: Context, resource: Int, private val objects: List<SimpleItemInfo>, private val listener: SimpleItemListener) :
    ArrayAdapter<SimpleItemInfo>(context, resource, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_text_simple, parent, false)
        val simpleItem = objects[position]
        (itemView as TextView).text = simpleItem.name()
        itemView.setOnClickListener { listener.onSimpleItemClick(simpleItem.type(), simpleItem) }
        return itemView
    }

    interface SimpleItemListener {
        fun onSimpleItemClick(type: Int, simpleItemInfo: SimpleItemInfo)
    }
}