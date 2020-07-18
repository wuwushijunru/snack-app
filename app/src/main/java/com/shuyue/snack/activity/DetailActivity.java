package com.shuyue.snack.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.shuyue.snack.MyApplication;
import com.shuyue.snack.R;
import com.shuyue.snack.model.Snack;
import com.shuyue.snack.utils.Tips;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.detailImage)
    ImageView image;

    @BindView(R.id.detailName)
    TextView name;

    @BindView(R.id.detailPrice)
    TextView price;

    @BindView(R.id.detailContent)
    TextView detail;

    @BindView(R.id.detailAddCartBtn)
    Button addCart;

    public static void actionStart(Context context, Snack snack) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("snack", snack);
        context.startActivity(intent);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        Snack snack = (Snack) getIntent().getSerializableExtra("snack");

        if (snack != null) {

            image.setImageResource(snack.getImage());
            name.setText(snack.getName());
            price.setText("￥" + snack.getPrice());
            detail.setText(snack.getDetail());

            addCart.setOnClickListener(v -> {
                if (!MyApplication.getCartSnacks().contains(snack)) {
                    // 添加到购物车
                    MyApplication.getCartSnacks().add(snack);
                    Tips.show("已添加" + snack.getName() + "到购物车");

                    // 关闭Activity
                    finish();
                } else {
                    Tips.show("已在购物车中，不能重复添加");
                }
            });
        }
    }
}