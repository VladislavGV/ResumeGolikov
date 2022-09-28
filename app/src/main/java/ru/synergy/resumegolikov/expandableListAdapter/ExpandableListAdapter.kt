package ru.synergy.resumegolikov.expandableListAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import ru.synergy.resumegolikov.R

class ExpandableListAdapter {

    class ExpandableListAdapter internal constructor(private val context: Context, private val resumeList: List<String>, private val exp1: HashMap<String, List<String>>):
        BaseExpandableListAdapter() {

        override fun getGroupCount(): Int {
            return resumeList.size
        }

        override fun getChildrenCount(groupPosition: Int): Int {
            return this.exp1[this.resumeList[groupPosition]]!!.size
        }

        override fun getGroup(groupPosition: Int): Any {
            return resumeList[groupPosition]
        }

        override fun getChild(groupPosition: Int, childPosition: Int): Any {
            return this.exp1[this.resumeList[groupPosition]]!![childPosition]
        }

        override fun getGroupId(groupPosition: Int): Long {
            return groupPosition.toLong()
        }

        override fun getChildId(groupPosition: Int, childPosition: Int): Long {
            return childPosition.toLong()
        }

        override fun hasStableIds(): Boolean {
            return false
        }

        override fun getGroupView(
            groupPosition: Int,
            isExpanded: Boolean,
            convertView: View?,
            parent: ViewGroup?
        ): View {
            var convertView = convertView
            val expTitle = getGroup(groupPosition) as String

            if (convertView == null){
                val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                convertView = inflater.inflate(R.layout.resume_list, null)

            }
            val resumeTv = convertView!!.findViewById<TextView>(R.id.experience_list)
            resumeTv.setText(expTitle)

            return convertView

        }

        override fun getChildView(
            groupPosition: Int,
            childPosition: Int,
            isLastChild: Boolean,
            convertView: View?,
            parent: ViewGroup?
        ): View {
            var convertView = convertView
            val placeTitle = getChildId(groupPosition, childPosition) as String

            if (convertView == null){
                val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                convertView = inflater.inflate(R.layout.exp1, null)

            }
            val placeTv = convertView!!.findViewById<TextView>(R.id.exp1_tv)
            placeTv.setText(placeTitle)

            return convertView
        }

        override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
            return true
        }

    }

}