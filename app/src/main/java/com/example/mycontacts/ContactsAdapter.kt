package com.example.mycontacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Contact(val name: String, val phoneNumber: String)

class ContactsAdapter(
    private val contacts: List<Contact>,
    private val onActionClick: (Contact, String) -> Unit
) : RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.contact_item, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.contactName.text = contact.name

        holder.callIcon.setOnClickListener {
            onActionClick(contact, "call")
        }

        holder.messageIcon.setOnClickListener {
            onActionClick(contact, "message")
        }
    }

    override fun getItemCount(): Int = contacts.size

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contactName: TextView = itemView.findViewById(R.id.textView_contactName)
        val callIcon: ImageView = itemView.findViewById(R.id.imageView_call)
        val messageIcon: ImageView = itemView.findViewById(R.id.imageView_message)
    }
}