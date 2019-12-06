package nexmo;

public final class webrtcinit {

    public webrtcinit()
    {

    }

    public void init()
    {
        org.webrtc.voiceengine.BuildInfo b = new  org.webrtc.voiceengine.BuildInfo();
        boolean audioManagerStereoInput = org.webrtc.voiceengine.WebRtcAudioManager.getStereoOutput();
        String audioRecordString = org.webrtc.voiceengine.WebRtcAudioRecord.class.toString();
        org.webrtc.voiceengine.WebRtcAudioTrack.setAudioTrackUsageAttribute(0);

    }
}
