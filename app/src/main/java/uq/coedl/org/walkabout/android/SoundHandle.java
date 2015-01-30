package uq.coedl.org.walkabout.android;

/**
 * Created by tp992853 on 30/01/2015.
 */
public class SoundHandle
{
    public String getName()
    {
        return name;
    }

    private String name;
    private int resId;
    private int handle;

    public SoundHandle(String name, int resId)
    {
        this.name = name;
        this.resId = resId;
    }

    public int getHandle()
    {
        return handle;
    }

    public void setHandle(int handle)
    {
        this.handle = handle;
    }

    public int getResId()
    {
        return resId;
    }
}
