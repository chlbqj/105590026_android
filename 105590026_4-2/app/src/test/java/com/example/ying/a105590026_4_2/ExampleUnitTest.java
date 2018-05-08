package com.example.ying.a105590026_4_2;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    BtnClick mBtnClick = new BtnClick();
    @Test
    public void addition_isCorrect() throws Exception {
        // 1 – 剪刀, 2 – 石頭, 3 – 布.
        assertEquals("恭喜，你贏了！", mBtnClick.handle(1,3));
        assertEquals("很可惜，你輸了！", mBtnClick.handle(1,2));
        assertEquals("雙方平手！", mBtnClick.handle(1,1));

        assertEquals("恭喜，你贏了！", mBtnClick.handle(2,1));
        assertEquals("很可惜，你輸了！", mBtnClick.handle(2,3));
        assertEquals("雙方平手！", mBtnClick.handle(2,2));

        assertEquals("恭喜，你贏了！", mBtnClick.handle(3,2));
        assertEquals("很可惜，你輸了！", mBtnClick.handle(3,1));
        assertEquals("雙方平手！", mBtnClick.handle(3,3));
    }

    @Test
    public void handle_isCorrect()throws Exception{
        assertEquals("剪刀",mBtnClick.iCom(1));
        assertEquals("石頭",mBtnClick.iCom(2));
        assertEquals("布",mBtnClick.iCom(3));

        assertEquals("剪刀",mBtnClick.iMy(1));
        assertEquals("石頭",mBtnClick.iMy(2));
        assertEquals("布",mBtnClick.iMy(3));
    }
}