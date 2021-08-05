package springpractice.reservationmovie.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springpractice.reservationmovie.domain.ScreeningInfo;
import springpractice.reservationmovie.repository.ScreeningInfoRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScreeningInfoService {

    private final ScreeningInfoRepository screeningInfoRepository;

    @Transactional
    public Long resister(ScreeningInfo screeningInfo) {
        screeningInfoRepository.save(screeningInfo);
        return screeningInfo.getId();
    }

    @Transactional
    public Long delete(ScreeningInfo screeningInfo) {
        screeningInfoRepository.delete(screeningInfo);
        return screeningInfo.getId();
    }

    public ScreeningInfo findOne(Long id) {
        return screeningInfoRepository.findById(id);
    }

    public List<ScreeningInfo> findByTitle(String title) {
        return screeningInfoRepository.findByTitle(title);
    }

    public List<ScreeningInfo> findAll() {
        return screeningInfoRepository.findAll();
    }
}
