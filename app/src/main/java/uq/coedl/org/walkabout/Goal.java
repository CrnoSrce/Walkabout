package uq.coedl.org.walkabout;

/**
 * Class representing one of the geographic points to be sought in the game
 * Created by Mark on 29/01/2015.
 */
public class Goal {
    //@TODO consider moving default values out into another class
    public static final String TEXT_DEFAULT_DESCRIPTION = "Next goal";

    //Geographic coordinates of goal
    private LocationInterface location;
    //Name of goal - e.g. to be displayed in a list of waypoints that people can select
    private String name;
    //Text description of goal
    private String description;
    //Textual message displayed on successfully reaching goal
    private String successMessage;
    //Filename (without path) of audio file to be played on success
    private String successAudio;
    //Filename (without path) of image file to be displayed on success
    private String successImage;

    /**
     * Convenience constructor initialising to default values where available, else null
     */
    public Goal() {
        this(null, null, null, null, null, null);
    }

    /**
     * Convenience constructor initialising Location and setting other members to default values
     * where available, else null
     */
    public Goal(LocationInterface location) {
        this(location, null, null, null, null, null);
    }

    /**
     * initialise Goal object
     *
     * @param location object with geographic coordinates of Goal
     * @param name Text name of this goal
     * @param description Text description of Goal - if null set to default value
     * @param successMessage Textual message displayed on successfully reaching goal - may be null
     * @param successAudio Filename (without path) of audio file to be played on success - may be null
     * @param successImage Filename (without path) of image file to be displayed on success - may be null
     */
    public Goal(LocationInterface location, String name, String description, String successMessage,
                String successAudio, String successImage) {
        //@TODO Consider whether to detect and respond to case where null location is passed in
        this.location = location;
        this.description = (description == null) ? TEXT_DEFAULT_DESCRIPTION : description;
        this.successMessage = successMessage;
        this.successAudio = successAudio;
        this.successImage = successImage;
    }

    /**
     *
     * @return Object containing geographic coordinates, or null
     */
    public LocationInterface getLocation() {
        return location;
    }

    /**
     * @return Name of goal, or null
     */
    public String getName() {
        return name;
    }

    /**
     * @return Description of goal
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return Message to display on sucess, or null
     */
    public String getSuccessMessage() {
        return successMessage;
    }

    /**
     * @return Filename of audio file (without path), or null
     */
    public String getSuccessAudio() {
        return successAudio;
    }

    /**
     * @return Filename of image file (without path), or null
     */
    public String getSuccessImage() {
        return successImage;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "location=" + location +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", successMessage='" + successMessage + '\'' +
                ", successAudio='" + successAudio + '\'' +
                ", successImage='" + successImage + '\'' +
                '}';
    }
}
