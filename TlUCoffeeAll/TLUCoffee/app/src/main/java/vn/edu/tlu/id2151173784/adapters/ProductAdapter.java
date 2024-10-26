package vn.edu.tlu.id2151173784.adapters;

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
import vn.edu.tlu.id2151173784.models.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> products;
    private OnProductClickListener listener;

    public interface OnProductClickListener {
        void onProductClick(int productId);
    }

    public ProductAdapter(List<Product> products, OnProductClickListener listener) {
        this.products = products;
        this.listener = listener;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = products.get(position);
        holder.textName.setText(product.getName());
        holder.textPrice.setText(String.format("%,d đ", product.getPrice()));
        Glide.with(holder.itemView.getContext())
            .load("http://10.0.2.2:8000/images/" + product.getImage())
            .into(holder.imageProduct);
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) { // Kiểm tra listener
                listener.onProductClick(product.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView textName;
        TextView textPrice;
        ImageView imageProduct;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.nameTextViewFragment);
            textPrice = itemView.findViewById(R.id.priceTextViewFragment);
            imageProduct = itemView.findViewById(R.id.productImageView);
        }
    }
}
