package vn.edu.tlu.id2151173784.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.edu.tlu.id2151173784.R;
import vn.edu.tlu.id2151173784.models.CartItem;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> cartItems;
    private Context context;

    public CartAdapter(List<CartItem> cartItems, Context context) {
        this.cartItems = cartItems;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);
        holder.productNameTextView.setText(cartItem.getProduct().getName());
        holder.productPriceTextView.setText(String.format("Giá: %d VND", cartItem.getProduct().getPrice()));
        holder.productQuantityTextView.setText(String.format("Số lượng: %d", cartItem.getQuantity()));

        // Load hình ảnh sản phẩm
        Glide.with(context)
                .load("http://10.0.2.2:8000/images/" + cartItem.getProduct().getImage())
                .into(holder.productImageView);
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        public TextView productNameTextView;
        public TextView productPriceTextView;
        public TextView productQuantityTextView;
        public ImageView productImageView;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.cart_item_name);
            productPriceTextView = itemView.findViewById(R.id.cart_item_price);
            productQuantityTextView = itemView.findViewById(R.id.cart_item_quantity);
            productImageView = itemView.findViewById(R.id.cart_item_image);
        }
    }
}
