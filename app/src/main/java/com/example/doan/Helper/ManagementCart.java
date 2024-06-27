package com.example.doan.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.doan.Model.CartItemModel;
import com.example.doan.Model.PopularModel;
import com.example.doan.Utils.Utils;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context){
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

//    public void insertFood(PopularModel item){
//        ArrayList<PopularModel> listPop = getListCart();
//        boolean existAlready = false;
//        int n =0;
//        for (int i = 0; i < listPop.size(); i++){
//            if(listPop.get(i).getTitle().equals(item.getTitle())){
//                existAlready = true;
//                n = i;
//                break;
//            }
//        }
//
//        if (existAlready){
////            listPop.get(n).setNumberinCart(item.getNumberinCart());
//        }else{
//            listPop.add(item);
//        }
//        tinyDB.putListObject("object_list_shoes", listPop);
//        Toast.makeText(context, "Added to your Cart", Toast.LENGTH_SHORT).show();
//
//    }

    public ArrayList<CartItemModel> getListCart() {
        return Utils.cartItemModels;
    }

    public void minusNumberItem(ArrayList<CartItemModel> listItem, int position, ChangeNumberItemsListener changeNumberItemsListener){
        if (listItem.get(position).getNumberInCart() == 1){
            listItem.remove(position);
        }else{
            listItem.get(position).setNumberInCart(listItem.get(position).getNumberInCart() - 1);
        }
//        tinyDB.putListObject("object_list_shoes", listItem);
        changeNumberItemsListener.change();
    }

    public void plusNumberItem(ArrayList<CartItemModel> listItem, int position, ChangeNumberItemsListener changeNumberItemsListener){
        listItem.get(position).setNumberInCart(listItem.get(position).getNumberInCart() + 1);
//        tinyDB.putListObject("object_list_shoes", listItem);
        changeNumberItemsListener.change();
    }

    public Double getTotal(){
        ArrayList<CartItemModel> listItem = getListCart();
        double total = 0.0;
        for (int i = 0; i < listItem.size(); i++){
            total = total + (listItem.get(i).getShoes().getPrice() * listItem.get(i).getNumberInCart());
        }

        return total;
    }

    public Double getTotalForCreate(ArrayList<CartItemModel> listItem){
        double total = 0.0;
        for (int i = 0; i < listItem.size(); i++){
            total = total + (listItem.get(i).getShoes().getPrice() * listItem.get(i).getNumberInCart());
        }

        return total;
    }
}
