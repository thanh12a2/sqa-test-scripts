package com.example.do_an_tot_nghiep.ValidatorUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.do_an_tot_nghiep.Helper.SingleLiveEvent;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Kiểm tra chức năng SingleLiveEvent: đảm bảo sự kiện chỉ gửi 1 lần, không bị lặp khi xoay màn hình.
 * Source: Helper/SingleLiveEvent.java
 */
public class StateManagementValidatorTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    /**
     * Mã: TC_SM_01
     * Chức năng: Gửi nhiều giá trị liên tiếp
     * Ý nghĩa: Gửi 3 giá trị A, B, C → người nghe phải nhận đủ 3 lần và giá trị cuối là "C"
     */
    @Test
    public void TC_SM_01_guiNhieuGiaTri_nhanDuVaDungGiaTriCuoi() {
        SingleLiveEvent<String> event = new SingleLiveEvent<>();
        AtomicInteger soLanNhan = new AtomicInteger(0);
        AtomicReference<String> giaTriCuoi = new AtomicReference<>();

        event.observeForever(s -> {
            soLanNhan.incrementAndGet();
            giaTriCuoi.set(s);
        });

        event.setValue("A");
        event.setValue("B");
        event.setValue("C");

        assertEquals(3, soLanNhan.get());
        assertEquals("C", giaTriCuoi.get());
    }

    /**
     * Mã: TC_SM_02
     * Chức năng: Gửi giá trị null
     * Ý nghĩa: Gửi null → người nghe nhận được null mà app không bị crash
     */
    @Test
    public void TC_SM_02_guiNull_nhanNullKhongCrash() {
        SingleLiveEvent<String> event = new SingleLiveEvent<>();
        AtomicReference<String> giaTriNhan = new AtomicReference<>("ban_dau");

        event.observeForever(giaTriNhan::set);
        event.setValue(null);

        assertNull(giaTriNhan.get());
    }

    /**
     * Mã: TC_SM_03
     * Chức năng: Gọi hàm call() (dùng cho sự kiện không có dữ liệu)
     * Ý nghĩa: Gọi call() → người nghe vẫn được kích hoạt dù không truyền giá trị
     */
    @Test
    public void TC_SM_03_goiCall_vanKichHoat() {
        SingleLiveEvent<Void> event = new SingleLiveEvent<>();
        AtomicInteger soLanNhan = new AtomicInteger(0);

        event.observeForever(v -> soLanNhan.incrementAndGet());
        event.call();

        assertEquals(1, soLanNhan.get());
    }
}
