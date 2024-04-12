package com.example.navigation.Crud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigation.R;

import java.text.MessageFormat;
import java.util.ArrayList;

public class UserrAdapter extends RecyclerView.Adapter<UserrAdapter.viewholder> {
    private final Context context;
    private final ArrayList<Ao> arrayList;
    private OnItemClickListener onItemClickListener;

    public UserrAdapter(Context context, ArrayList<Ao> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_list_item, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.name.setText(MessageFormat.format("{0} {1}", arrayList.get(position).getFirstName(), arrayList.get(position).getLastName()));
        holder.phone.setText(arrayList.get(position).getPhone());
        holder.bio.setText(arrayList.get(position).getBio());
        //Trong đoạn mã trên, khi người dùng nhấn vào một mục danh sách, Adapter sẽ kiểm tra
        // xem onItemClickListener đã được thiết lập hay không. Nếu đã thiết lập, nó sẽ gọi
        // phương thức onClick() và truyền vào đối tượng Ao tại vị trí tương ứng trong danh sách.
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onClick(arrayList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView name, phone, bio;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.list_item_name);
            phone = itemView.findViewById(R.id.list_item_phone);
            bio = itemView.findViewById(R.id.list_item_bio);
        }
    }

    //OnItemClickListener là một Interface định nghĩa một phương thức chứa logic sự kiện (event logic).
    // Trong trường hợp này, có một phương thức onClick với tham số là một đối tượng của lớp Ao.
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    //Đây là một phương thức public được sử dụng để thiết lập một đối tượng nghe sự kiện (OnItemClickListener).
    // Khi bạn gọi phương thức này và truyền vào một đối tượng của lớp implement OnItemClickListener,
    // thì onItemClickListener bên trong lớp UserrAdapter sẽ trở thành một phiên bản của đối tượng được truyền vào.
    //
    //Điều này rất hữu ích trong việc thiết lập các tương tác giữa các thành phần của ứng dụng.
    // Thay vì phải gắn kết logic xử lý sự kiện trực tiếp trong Adapter, bạn có thể định nghĩa
    // một đối tượng nghe sự kiện ở bên ngoài và truyền nó vào Adapter thông qua phương thức này.
    public interface OnItemClickListener {
        void onClick(Ao ao); // Đổi User thành tên lớp dữ liệu bạn đang sử dụng
    }
}
