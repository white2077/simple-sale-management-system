package com.white.assignmentjava5;

import com.white.assignmentjava5.entity.SanPham;
import com.white.assignmentjava5.repository.ISanPhamChiTietRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

@SpringBootTest
class AssignmentJava5ApplicationTests {
    @Autowired
    private ISanPhamChiTietRepository sanPhamChiTietRepository;

}
