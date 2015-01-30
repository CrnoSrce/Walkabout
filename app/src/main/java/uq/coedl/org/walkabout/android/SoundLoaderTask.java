package uq.coedl.org.walkabout.android;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.AsyncTask;

public class SoundLoaderTask extends AsyncTask<Void, Void, Void>
{
    private boolean finished;

    public interface LoadSoundContext
    {
        public Context getContext();
        public void setSoundPool(SoundPool soundPool);
    }

    private LoadSoundContext loadSoundContext = null;
    private SoundHandle[] soundHandles = null;

    public SoundLoaderTask(LoadSoundContext loadSoundContext, SoundHandle[] soundHandles)
    {
        finished = false;
        this.loadSoundContext = loadSoundContext;
        this.soundHandles = soundHandles;
    }

    @Override
    protected Void doInBackground(Void... params)
    {
        finished = false;
        if(soundHandles.length > 0)
        {
            SoundPool soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
            loadSoundContext.setSoundPool(soundPool);

            for(SoundHandle soundHandle : soundHandles)
            {
                if(loadSoundContext.getContext() != null)
                {
                    soundHandle.setHandle(soundPool.load(loadSoundContext.getContext(), soundHandle.getResId(), 1));
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid)
    {
        finished = true;
    }

    public boolean isFinished()
    {
        return finished;
    }
}