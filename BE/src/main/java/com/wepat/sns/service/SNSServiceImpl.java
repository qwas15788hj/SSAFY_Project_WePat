package com.wepat.sns.service;

import com.wepat.photo.PhotoDto;
import com.wepat.sns.repository.SNSRepository;
import com.wepat.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class SNSServiceImpl implements SNSService {
    private final SNSRepository snsRepository;
    private final int CALENDAR_UNIT = 1;

    @Override
    public List<PhotoDto> getSNS(int before, String memberId) throws ExecutionException, InterruptedException {
        Date date = new Date();
        date = DateUtil.addDays(date, Calendar.DATE, -before);
        String strDate  = DateUtil.getStringDate(date);
        if (before == 0) {
            strDate = "0";
        }
        return snsRepository.getSNS(strDate, memberId);
    }

    @Override
    public PhotoDto getSNSByPhotoId(String photoId) throws ExecutionException, InterruptedException {
        return snsRepository.getSNSByPhotoId(photoId);
    }

    @Override
    public int updateSNSLikeByPhotoId(String photoId) throws ExecutionException, InterruptedException {
        return snsRepository.updateSNSLikeByPhotoId(photoId);
    }

    @Override
    public void reportSNSByPhotoId(String photoId, String memberId) throws ExecutionException, InterruptedException {
        snsRepository.reportSNSByPhotoId(photoId, memberId);
    }

    @Override
    public List<PhotoDto> getReportedList() throws ExecutionException, InterruptedException {
        return snsRepository.getReportedList();
    }

    @Override
    public void blockSNSByPhotoId(String photoId) throws ExecutionException, InterruptedException {
        snsRepository.blockSNSByPhotoId(photoId);
    }
}
