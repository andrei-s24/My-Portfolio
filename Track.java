
public class Track implements Comparable<Track>{
    private String title;
    private String artist;
    private int year;
    private int length;
    
    public Track(String title, String artist){
        this.title = title;
        this.artist = artist;
        this.year = 0;
        this.length = 0;
    }
    
    public Track(String title, String artist, int year, int length){
        this.title = title;
        this.artist = artist;
        this.year = year;
        this.length = length;
    }
    
    public String getArtist(){
        return this.artist;
    }
    
    public int getYear(){
        return this.year;
    }
    
    public String getTitle(){
        return this.title;
    }
    
    public String toString(){
        String yearAsString = "";
        String lengthAsString = "";
        int min = this.length / 60;
        int sec = this.length % 60;
        if(min > 0 || sec > 0 && this.year > 0){
            lengthAsString = min + ":" + sec;
            yearAsString = String.valueOf(this.year);
        }                
        return title + " by " + artist + " " + yearAsString + " - " + lengthAsString;
    }
    
    public int compareTo(Track other){
        return title.toLowerCase().compareTo((other.title).toLowerCase());
    }
    
}
