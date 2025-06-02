package taxiapp.driver.repository

import org.springframework.data.jpa.repository.JpaRepository
import taxiapp.driver.model.DriverLegalInfo

interface DriverLegalInfoRepository : JpaRepository<DriverLegalInfo, Long> {
}