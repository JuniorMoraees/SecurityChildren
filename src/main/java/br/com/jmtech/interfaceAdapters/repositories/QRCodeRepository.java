package br.com.jmtech.interfaceAdapters.repositories;

import br.com.jmtech.infrastructure.domains.QRCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QRCodeRepository extends JpaRepository<QRCode, Long> {
}
