package com.egabruskiy.tasktimer.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import com.egabruskiy.tasktimer.MainActivity
import com.egabruskiy.tasktimer.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_chooser.listView

class ChooserActivity : AppCompatActivity(), AdapterView.OnItemClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chooser)
        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser!=null && currentUser.isEmailVerified ){
            val intent = Intent(this, MainActivity::class.java)
                                        startActivity(intent)
        }

        val adapter = MyArrayAdapter(this, android.R.layout.simple_list_item_2, CLASSES)
        adapter.setDescriptionIds(DESCRIPTION_IDS)
        adapter.setTitleIds(ITEM_NAME_IDS)
        listView.adapter = adapter
        listView.onItemClickListener = this
    }

    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        val clicked: Class<*> = CLASSES[position]
        startActivity(Intent(this, clicked))
    }

    class MyArrayAdapter(
        private val ctx: Context,
        resource: Int,
        private val classes: Array<Class<*>>
    )
        : ArrayAdapter<Class<*>>(ctx, resource, classes) {
        private var descriptionIds: IntArray? = null
        private var titleIds: IntArray? = null

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
            var view = convertView

            if (convertView == null) {
                val inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                view = inflater.inflate(android.R.layout.simple_list_item_2, null)
            }

            view?.findViewById<TextView>(android.R.id.text1)?.setText(titleIds!![position])
            view?.findViewById<TextView>(android.R.id.text2)?.setText(descriptionIds!![position])

            return view
        }

        fun setDescriptionIds(descriptionIds: IntArray) {
            this.descriptionIds = descriptionIds
        }
        fun setTitleIds(titleIds: IntArray) {
            this.titleIds = titleIds
        }
    }

    companion object {
        private val CLASSES = arrayOf(
                EmailPasswordActivity::class.java,
                AnonymousAuthActivity::class.java) as Array<Class<*>>
        private val DESCRIPTION_IDS = intArrayOf(
                R.string.desc_emailpassword,
        R.string.desc_anonymous_auth
        )
        private val ITEM_NAME_IDS = intArrayOf(
                R.string.title_emailpass,
                R.string.title_anonymous
        )
    }
}
