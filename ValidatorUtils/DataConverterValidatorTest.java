package com.example.do_an_tot_nghiep.ValidatorUtils;

import static org.junit.Assert.assertEquals;

import com.example.do_an_tot_nghiep.Helper.DataConverter;

import org.junit.Test;

/**
 * Kiểm tra chức năng tách giờ và phút từ chuỗi thời gian "HH:mm".
 * Source: Helper/DataConverter.java
 */
public class DataConverterValidatorTest {

    // ===== LẤY GIỜ =====

    /**
     * Mã: TC_DC_01
     * Chức năng: Tách giờ từ chuỗi thời gian
     * Ý nghĩa: Nhập "14:30" → hệ thống tách ra được phần giờ là 14
     */
    @Test
    public void TC_DC_01_chuoiChuan_layDungGio() {
        assertEquals(14, DataConverter.layGio("14:30"));
    }

    /**
     * Mã: TC_DC_02
     * Chức năng: Xử lý khi không có dữ liệu
     * Ý nghĩa: Truyền null → hệ thống trả về 0 thay vì bị crash
     */
    @Test
    public void TC_DC_02_null_traVe0() {
        assertEquals(0, DataConverter.layGio(null));
    }

    /**
     * Mã: TC_DC_03
     * Chức năng: Phát hiện lỗi khi giờ chỉ có 1 chữ số
     * Ý nghĩa: Nhập "9:30" (thiếu số 0 phía trước) → hệ thống bị crash vì cắt chuỗi sai
     * (Đây là BUG THẬT: code lấy "9:" rồi chuyển sang số → lỗi)
     */
    @Test(expected = NumberFormatException.class)
    public void TC_DC_03_gio1ChuSo_seVangLoi() {
        DataConverter.layGio("9:30");
    }

    /**
     * Mã: TC_DC_04
     * Chức năng: Phát hiện lỗi khi nhập chữ thay vì số
     * Ý nghĩa: Nhập "ab:cd" → hệ thống bị crash vì không chuyển chữ sang số được
     * (Đây là BUG THẬT: code không kiểm tra đầu vào có phải số không)
     */
    @Test(expected = NumberFormatException.class)
    public void TC_DC_04_chuoiKhongPhaiSo_seVangLoi() {
        DataConverter.layGio("ab:cd");
    }


    // ===== LẤY PHÚT =====

    /**
     * Mã: TC_DC_05
     * Chức năng: Tách phút từ chuỗi thời gian
     * Ý nghĩa: Nhập "10:45" → hệ thống tách ra được phần phút là 45
     */
    @Test
    public void TC_DC_05_chuoiChuan_layDungPhut() {
        assertEquals(45, DataConverter.layPhut("10:45"));
    }

    /**
     * Mã: TC_DC_06
     * Chức năng: Phát hiện lỗi khi phần phút là chữ
     * Ý nghĩa: Nhập "10:ab" → hệ thống bị crash vì phần phút không phải số
     * (Đây là BUG THẬT: code không validate phần phút)
     */
    @Test(expected = NumberFormatException.class)
    public void TC_DC_06_phutKhongPhaiSo_seVangLoi() {
        DataConverter.layPhut("10:ab");
    }

    /**
     * Mã: TC_DC_07
     * Chức năng: Xử lý chuỗi bị cắt ngắn
     * Ý nghĩa: Nhập "10:" (thiếu phần phút) → hệ thống trả về 0 thay vì crash
     */
    @Test
    public void TC_DC_07_chuoiThieuPhut_traVe0() {
        assertEquals(0, DataConverter.layPhut("10:"));
    }
}
