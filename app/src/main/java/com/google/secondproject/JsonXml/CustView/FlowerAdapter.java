package com.google.secondproject.JsonXml.CustView;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.graphics.Point;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.secondproject.DataBase.FlowerDBHelper;
import com.google.secondproject.JsonXml.Element.Flower;
import com.google.secondproject.R;

import java.util.List;

public class FlowerAdapter extends ArrayAdapter {

    private Activity activity;
    private List<Flower> flowerList;
    private FlowerDBHelper dbHelper;

    public FlowerAdapter(@NonNull Activity activity, List<Flower> flowerList) {
        super(activity, R.layout.flower_list_item, flowerList);
        this.activity = activity;
        this.flowerList = flowerList;
        dbHelper = new FlowerDBHelper(activity);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        viewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(activity).inflate(R.layout.flower_list_item, parent, false);
            holder = new viewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }
        holder.fill(position);
        return convertView;
    }

    public class viewHolder {
        ImageView flowerImg, iconMore;
        TextView flowerName, flowerCategory, flowerPrice;

        public viewHolder(View view) {
            flowerCategory = view.findViewById(R.id.flower_category);
            flowerImg = view.findViewById(R.id.flower_img);
            flowerName = view.findViewById(R.id.flower_name);
            flowerPrice = view.findViewById(R.id.flower_price);
            iconMore = view.findViewById(R.id.icon_more);
        }

        public void fill(int position) {
            final Flower flower = flowerList.get(position);
            flowerName.setText(flower.getName());
            flowerPrice.setText(flower.getPrice() + " $");
            flowerCategory.setText(flower.getCategory());

            //load image
            String photoName = flower.getPhoto();
            if (photoName.contains(".")) {
                photoName = photoName.substring(0, photoName.lastIndexOf('.'));
            }
            int imgResId = activity.getResources().getIdentifier(
                    photoName, "drawable", activity.getApplicationContext().getPackageName());
            flowerImg.setImageResource(imgResId);

            //icon more
            final PopupMenu popup = new PopupMenu(iconMore.getContext(), iconMore);
            popup.inflate(R.menu.flower_popup_menu);
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    int id = item.getItemId();
                    if (id == R.id.popup_option_delete) {
                        dbHelper.delete(flower.getProductId());
                        flowerList.remove(position);
                        notifyDataSetChanged();
                    } else if (id == R.id.popup_option_edit) {
                        showEditDialog(flower);
                    }
                    return false;
                }
            });
            iconMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popup.show();
                }
            });
        }

        private void showEditDialog(final Flower flower) {
            final Dialog dialog = new Dialog(activity);
            dialog.setContentView(R.layout.update_db);

            changeDialogSize(dialog);

            Button updateSubmit;
            final EditText updateName, updateCat, updatePrice, updateIns;

            updateSubmit = dialog.findViewById(R.id.btn_submit_update);
            updateName = dialog.findViewById(R.id.update_name);
            updateCat = dialog.findViewById(R.id.update_cat);
            updatePrice = dialog.findViewById(R.id.update_price);
            updateIns = dialog.findViewById(R.id.update_ins);

            updateName.setText(flower.getName());
            updateCat.setText(flower.getCategory());
            updatePrice.setText(String.valueOf(flower.getPrice()));
            updateIns.setText(flower.getInstructions());
            dialog.show();

            updateSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String pr = updatePrice.getText().toString().trim();
                    Double price = pr.isEmpty() ? 0.0 : Double.valueOf(pr);
                    flower.setPrice(price);
                    flower.setName(updateName.getText().toString().trim());
                    flower.setInstructions(updateIns.getText().toString().trim());
                    flower.setCategory(updateCat.getText().toString().trim());
                    ContentValues values = flower.getContentValues();
                    values.remove(Flower.KEY_ID);
                    dbHelper.update(flower.getProductId(), values);
                    notifyDataSetChanged();
                    dialog.dismiss();
                }
            });

        }

        private Point getScreenSize(Activity activity) {
            Point point = new Point();
            activity.getWindowManager().getDefaultDisplay().getSize(point);
            return point;
        }

        private void changeDialogSize(Dialog dialog) {
            Point point = getScreenSize(activity);
            if (dialog.getWindow() != null) {
                dialog.getWindow().setLayout((int) (0.9 * point.x), ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        }
    }
}
