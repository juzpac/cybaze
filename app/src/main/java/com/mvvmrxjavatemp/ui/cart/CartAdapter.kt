package com.mvvmrxjavatemp.ui.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mvvmrxjavatemp.data.model.api.response.GetCartResponse
import com.squareup.picasso.Picasso
import com.template.R
import kotlinx.android.synthetic.main.cart_list_item.view.*


class CartAdapter(private val context: Context, val dataList: List<GetCartResponse.Data.Product>) : RecyclerView.Adapter<CartAdapter.MyViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private lateinit var view: View

    //This method inflates view present in the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        view = inflater.inflate(R.layout.cart_list_item, parent, false)
        return MyViewHolder(view)
    }

    //Binding the data using get() method of POJO object
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItems = dataList[position]
        holder.bindView(listItems)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    //View holder class, where all view components are defined
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(listItems: GetCartResponse.Data.Product) {
            itemView.qtyTVInCart
            itemView.apply {
                Picasso.get().load(listItems.thumbUrl).into(imageInCart);
                textInCart.text=listItems.title
                qtyTVInCart.text="Qty:"+listItems.quantity
            }
        }
    }
}