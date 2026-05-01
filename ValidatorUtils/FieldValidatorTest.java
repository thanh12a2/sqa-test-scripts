package com.example.do_an_tot_nghiep.ValidatorUtils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.do_an_tot_nghiep.Helper.FieldValidator;

import org.junit.Test;

/**
 * Kiểm tra chức năng validate dữ liệu nhập trên form đặt lịch, đăng nhập, gửi email.
 * Source: Helper/FieldValidator.java
 */
public class FieldValidatorTest {

    // ===== KIỂM TRA TRƯỜNG BẮT BUỘC =====

    /**
     * Mã: TC_FV_01
     * Chức năng: Kiểm tra trường bắt buộc
     * Ý nghĩa: Khi người dùng điền đầy đủ tất cả các ô trên form → hệ thống chấp nhận
     */
    @Test
    public void TC_FV_01_dienDayDu_hopLe() {
        assertTrue(FieldValidator.validateFields("Nguyen Van A", "0794104124", "10:30"));
    }

    /**
     * Mã: TC_FV_02
     * Chức năng: Kiểm tra trường bắt buộc
     * Ý nghĩa: Khi có 1 ô bị bỏ trống giữa các ô đã điền → hệ thống từ chối
     */
    @Test
    public void TC_FV_02_tronLanTrongVaHopLe_khongHopLe() {
        assertFalse(FieldValidator.validateFields("Nguyen Van A", "", "10:30"));
    }

    /**
     * Mã: TC_FV_03
     * Chức năng: Kiểm tra trường bắt buộc
     * Ý nghĩa: Khi không truyền ô nào cả (form trống hoàn toàn) → hệ thống chấp nhận vì không có gì sai
     */
    @Test
    public void TC_FV_03_khongTruyenTruongNao_traVeTrue() {
        assertTrue(FieldValidator.validateFields());
    }


    // ===== KIỂM TRA SỐ ĐIỆN THOẠI =====

    /**
     * Mã: TC_FV_04
     * Chức năng: Kiểm tra số điện thoại
     * Ý nghĩa: Nhập đúng 10 chữ số → hệ thống chấp nhận
     */
    @Test
    public void TC_FV_04_sdt10So_hopLe() {
        assertTrue(FieldValidator.isValidPhoneNumber("0794104124"));
    }

    /**
     * Mã: TC_FV_05
     * Chức năng: Kiểm tra số điện thoại có dấu cách
     * Ý nghĩa: Người dùng nhập SĐT có dấu cách "079 410 4124" → hệ thống từ chối
     * (Lưu ý: đây là bug tiềm ẩn vì người dùng hay gõ SĐT có dấu cách)
     */
    @Test
    public void TC_FV_05_sdtCoDauCach_khongHopLe() {
        assertFalse(FieldValidator.isValidPhoneNumber("079 410 4124"));
    }

    /**
     * Mã: TC_FV_06
     * Chức năng: Kiểm tra số điện thoại quốc tế
     * Ý nghĩa: Nhập SĐT dạng quốc tế "+84794104124" → hệ thống từ chối
     * (Lưu ý: dạng +84 là hợp lệ nhưng regex chưa hỗ trợ)
     */
    @Test
    public void TC_FV_06_sdtQuocTe_biTuChoi() {
        assertFalse(FieldValidator.isValidPhoneNumber("+84794104124"));
    }

    /**
     * Mã: TC_FV_07
     * Chức năng: Kiểm tra SĐT ở giới hạn cho phép
     * Ý nghĩa: Nhập đúng 9 số (tối thiểu) và 11 số (tối đa) → cả hai đều hợp lệ
     */
    @Test
    public void TC_FV_07_sdtBienDuoiVaBienTren_hopLe() {
        assertTrue(FieldValidator.isValidPhoneNumber("079410412"));    // 9 số
        assertTrue(FieldValidator.isValidPhoneNumber("07941041234"));  // 11 số
    }


    // ===== KIỂM TRA EMAIL =====

    /**
     * Mã: TC_FV_08
     * Chức năng: Kiểm tra email
     * Ý nghĩa: Nhập email đúng chuẩn "phong.kaster@gmail.com" → hệ thống chấp nhận
     */
    @Test
    public void TC_FV_08_emailChuan_hopLe() {
        assertTrue(FieldValidator.isValidEmail("phong.kaster@gmail.com"));
    }

    /**
     * Mã: TC_FV_09
     * Chức năng: Kiểm tra email thiếu đuôi
     * Ý nghĩa: Nhập "phong@gmail" (thiếu .com) → hệ thống từ chối
     */
    @Test
    public void TC_FV_09_emailThieuDuoi_khongHopLe() {
        assertFalse(FieldValidator.isValidEmail("phong@gmail"));
    }

    /**
     * Mã: TC_FV_10
     * Chức năng: Kiểm tra email có 2 dấu chấm liên tiếp
     * Ý nghĩa: Nhập "phong..k@gmail.com" → theo chuẩn thì sai, nhưng regex có thể cho qua
     * (Lưu ý: đây là bug tiềm ẩn trong regex)
     */
    @Test
    public void TC_FV_10_emailHaiChamLienTiep_khongHopLe() {
        assertFalse(FieldValidator.isValidEmail("phong..k@gmail.com"));
    }
}
