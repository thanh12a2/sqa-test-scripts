package com.example.do_an_tot_nghiep.ValidatorUtils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.do_an_tot_nghiep.Helper.WorkingHourValidator;

import org.junit.Test;

/**
 * Kiểm tra chức năng xác định giờ hành chính của bệnh viện.
 * Source: Helper/WorkingHourValidator.java
 */
public class WorkingHourValidatorTest {

    // ===== GIỜ KHÁM BỆNH (7h - 18h) =====

    /**
     * Mã: TC_WH_01
     * Chức năng: Kiểm tra giờ mở cửa và đóng cửa phòng khám
     * Ý nghĩa: 7h sáng (mở cửa) và 18h chiều (đóng cửa) → cả hai đều trong giờ khám
     */
    @Test
    public void TC_WH_01_dungHaiBien_hopLe() {
        assertTrue(WorkingHourValidator.trongGioKham(7));
        assertTrue(WorkingHourValidator.trongGioKham(18));
    }

    /**
     * Mã: TC_WH_02
     * Chức năng: Kiểm tra ngay trước giờ mở cửa và sau giờ đóng cửa
     * Ý nghĩa: 6h sáng (trước mở cửa) và 19h tối (sau đóng cửa) → đều ngoài giờ khám
     */
    @Test
    public void TC_WH_02_ngayNgoaiBien_khongHopLe() {
        assertFalse(WorkingHourValidator.trongGioKham(6));
        assertFalse(WorkingHourValidator.trongGioKham(19));
    }

    /**
     * Mã: TC_WH_03
     * Chức năng: Kiểm tra giá trị giờ bất thường (số âm)
     * Ý nghĩa: Giờ = -1 (không tồn tại) → hệ thống phải trả về false
     */
    @Test
    public void TC_WH_03_gioAm_khongHopLe() {
        assertFalse(WorkingHourValidator.trongGioKham(-1));
    }

    /**
     * Mã: TC_WH_04
     * Chức năng: Kiểm tra giá trị giờ bất thường (giờ 24)
     * Ý nghĩa: Giờ = 24 (không tồn tại trong đồng hồ) → hệ thống phải trả về false
     */
    @Test
    public void TC_WH_04_gio24_khongHopLe() {
        assertFalse(WorkingHourValidator.trongGioKham(24));
    }


    // ===== GIỜ GỬI THÔNG BÁO (7h - 20h) =====

    /**
     * Mã: TC_WH_05
     * Chức năng: So sánh giờ khám và giờ thông báo với cùng 1 giá trị
     * Ý nghĩa: 19h tối → phòng khám đã đóng cửa (false) nhưng thông báo vẫn gửi được (true)
     */
    @Test
    public void TC_WH_05_19h_khacNhauGiuaKhamVaThongBao() {
        assertFalse(WorkingHourValidator.trongGioKham(19));
        assertTrue(WorkingHourValidator.trongGioThongBao(19));
    }

    /**
     * Mã: TC_WH_06
     * Chức năng: Kiểm tra giờ khuya
     * Ý nghĩa: 21h đêm → cả phòng khám lẫn thông báo đều không hoạt động
     */
    @Test
    public void TC_WH_06_21hDem_caDeuNgoaiGio() {
        assertFalse(WorkingHourValidator.trongGioKham(21));
        assertFalse(WorkingHourValidator.trongGioThongBao(21));
    }
}
