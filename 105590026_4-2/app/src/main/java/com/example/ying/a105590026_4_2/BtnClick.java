package com.example.ying.a105590026_4_2;

import android.widget.Button;

public class BtnClick {

    String handle(int _myhandle, int _computerhandle){
        // 1 – 剪刀, 2 – 石頭, 3 – 布.
        if(_myhandle==1){
            if(_computerhandle==1){
                return "雙方平手！";
            }
            else if(_computerhandle==2){
                return "很可惜，你輸了！";
            }
            else {
                return "恭喜，你贏了！";
            }
        }
        else if(_myhandle==2){
            if(_computerhandle==1){
                return "恭喜，你贏了！";
            }
            else if(_computerhandle==2){
                return "雙方平手！";
            }
            else {
                return "很可惜，你輸了！";
            }
        }
        else {
            if(_computerhandle==1){
                return "很可惜，你輸了！";
            }
            else if(_computerhandle==2){
                return "恭喜，你贏了！";
            }
            else {
                return "雙方平手！";
            }
        }
    }
    String iCom(int _iCom)
    {
        if(_iCom==1){
            return "剪刀";
        }
        else if(_iCom==2){
            return "石頭";
        }
        else {
            return "布";
        }
    }
    String iMy(int _iMy){
        if(_iMy==1){
            return "剪刀";
        }
        else if(_iMy==2){
            return "石頭";
        }
        else {
            return "布";
        }
    }
}
