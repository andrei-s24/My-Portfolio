import java.util.ArrayList;
public class Playlist{
    private String name;
    private ArrayList<Track> playlist;    
    public Playlist(){
        playlist = new ArrayList<Track>();    
    }
    
    public String toString(){
        String playlistAsString = "";
        for(int i = 0; i < playlist.size(); i++){
            playlistAsString = playlistAsString + playlist.get(i).toString() + "/n";  
        }
        return playlistAsString;
    }
    
    public void add(String title, String artist){
        Track track = new Track(title, artist);
        playlist.add(track);
    }
    
    public void add(String title, String artist, int year, int length){
        Track track = new Track(title, artist, year, length);
        playlist.add(track);
    }
    
    public void add(Track t){
        playlist.add(t);
    }
    
    public boolean remove(String title){
        boolean removed = false;
        for(int i = 0; i < playlist.size(); i++){
            if(playlist.get(i).getTitle().equalsIgnoreCase(title)){
                playlist.remove(i);
                removed = true;
                break;
            }            
        }
        return removed;
    }
    
    public void showList(){
        if(playlist.size() == 0){
            System.out.println("The list is empty");
        }
        else{
            for(int i = 0; i < playlist.size(); i++){
                System.out.println(playlist.get(i).toString());
            } 
        }
    }
    
    public void play(boolean random){
        ArrayList<Track> temp;
        temp = new ArrayList<Track>(playlist);                
        if(random == false){
            System.out.println("Playing:");
            for(int i = 0; i < playlist.size(); i++){
                System.out.println(playlist.get(i).toString());
            }
        }
        else{
            Track x;
            System.out.println("Shuffle Play:");
            for(int i = 0; i < playlist.size(); i++){
                x = temp.get((int)(Math.random() * temp.size()));
                System.out.println(x.toString());
                temp.remove(x);
            }
        }        
    }
    
    public void playOnly(String artist){
        System.out.println("Play only " + artist + ":");
        for(int i = 0; i < playlist.size(); i++){
            if(playlist.get(i).getArtist() == artist){
                System.out.println(playlist.get(i).toString());
            }
        }       
    }
    
    public void playOnly(int year){
        System.out.println("Play only " + year + ":");
        for(int i = 0; i < playlist.size(); i++){
            if(playlist.get(i).getYear() == year){
                System.out.println(playlist.get(i).toString());
            }    
        }
    }
}
