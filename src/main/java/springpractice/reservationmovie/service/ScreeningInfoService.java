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
    public void resister(ScreeningInfo screeningInfo) {
        screeningInfoRepository.save(screeningInfo);
    }

    @Transactional
    public void delete(ScreeningInfo screeningInfo) {
        screeningInfoRepository.delete(screeningInfo);
    }

    public ScreeningInfo searchById(Long id) {
        return screeningInfoRepository.findById(id);
    }

    public List<ScreeningInfo> searchByTitle(String title) {
        return screeningInfoRepository.findByTitle(title);
    }

    public List<ScreeningInfo> searchAll() {
        return screeningInfoRepository.findAll();
    }
}
