package vn.edu.tlu.id2151173784.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.tlu.id2151173784.models.Order;
import vn.edu.tlu.id2151173784.R;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.OrderViewHolder> {
    private List<Order> orders;
    TextView tvOrderID, tvOrderDate, tvTotalAmount;

    public OrderHistoryAdapter(List<Order> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.tvOrderID.setText("Mã đơn hàng: #" + order.getId());
        holder.tvOrderDate.setText("Ngày đặt: " + order.getOrderDate());
//        holder.tvTotalAmount.setText("Tổng tiền: " + order.getTotalAmount() + " VND");
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView tvOrderID, tvOrderDate, tvTotalAmount;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOrderID = itemView.findViewById(R.id.tvOrderID);
            tvOrderDate = itemView.findViewById(R.id.tvOrderDate);
            tvTotalAmount = itemView.findViewById(R.id.tvTotalAmount);
        }
    }
}
