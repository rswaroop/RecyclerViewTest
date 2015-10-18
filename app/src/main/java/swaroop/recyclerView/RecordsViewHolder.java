package swaroop.recyclerView;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class RecordsViewHolder extends RecyclerView.ViewHolder {
    CardView cardItemLayout;
    TextView name;
    TextView value;

    public RecordsViewHolder(View itemView) {
        super(itemView);

        cardItemLayout = (CardView) itemView.findViewById(R.id.cardlist_item);
        name = (TextView) itemView.findViewById(R.id.list_name);
        value = (TextView) itemView.findViewById(R.id.list_value);
    }
}
