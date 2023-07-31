package br.com.geshner.postalcodesearch.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.geshner.postalcodesearch.databinding.LayoutItemAddressBinding
import br.com.geshner.postalcodesearch.domain.Address

class AddressAdapter : ListAdapter<Address, AddressAdapter.AddressViewHolder>(
    object : DiffUtil.ItemCallback<Address>() {
        override fun areItemsTheSame(oldItem: Address, newItem: Address): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Address, newItem: Address): Boolean {
            return oldItem.postalCode == newItem.postalCode &&
                    oldItem.address1 == newItem.address1 &&
                    oldItem.address2 == newItem.address2 &&
                    oldItem.address3 == newItem.address3
        }

    }
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AddressAdapter.AddressViewHolder {
        val binding = LayoutItemAddressBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return AddressViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddressAdapter.AddressViewHolder, position: Int) {
        holder.bind(getItem(holder.adapterPosition))
    }

    inner class AddressViewHolder(
        private val binding: LayoutItemAddressBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(address: Address) {
            binding.apply {
                tvPostalcode.text = address.postalCode
                tvAddress1.text = address.address1
                tvAddress2.text = address.address2
                tvAddress3.text = address.address3
            }
        }
    }
}