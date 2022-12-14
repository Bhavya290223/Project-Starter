package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// represents a list of Contents

public class ListOfContent implements Writable {
    private String name;
    private List<Content> loC;
    private int position;


    //EFFECTS - creates a new List of Content
    public ListOfContent(String name) {
        this.name = name;
        loC = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    //EFFECTS: returns the List of Content
    public List<Content> getList() {
        return loC;
    }

    //EFFECTS: checks whether the list contains a Content with the given name
    public boolean contains(String cn) {
        for (Content l : loC) {
            if (cn.equals(l.getName())) {
                return true;
            }
        }
        return false;
    }

    //EFFECTS: returns the List of names of contents
    public List<String> getNames() {
        List<String> n = new ArrayList<>();
        for (Content c : loC) {
            n.add(c.getName());
        }
        return n;
    }

    //EFFECTS: returns the List of genres of contents
    public List<String> getGenres() {
        List<String> n = new ArrayList<>();
        for (Content c : loC) {
            n.add(c.getGenre());
        }
        return n;
    }

    //EFFECTS: returns the List of languages of contents
    public List<String> getLanguages() {
        List<String> n = new ArrayList<>();
        for (Content c : loC) {
            n.add(c.getLanguage());
        }
        return n;
    }

    //EFFECTS: returns the List of sites of contents
    public List<String> getLocation() {
        List<String> n = new ArrayList<>();
        for (Content c : loC) {
            n.add(c.getLocation());
        }
        return n;
    }

    //EFFECTS: returns the List of contents by given year
    public List<String> contentbyYear(int newYear) {
        List<String> n = new ArrayList<>();
        for (Content c : loC) {

            if (newYear > 1800 && newYear == c.getYear()) {
                n.add(c.getName());
            }
        }
        EventLog.getInstance().logEvent(new Event("Filtered LOC by Year: " + newYear));
        return n;
    }

    //EFFECTS: returns the List of contents by given genre
    public List<String> contentbyGenre(String newGenre) {
        List<String> n = new ArrayList<>();
        for (Content c : loC) {

            if (newGenre.equals(c.getGenre())) {
                n.add(c.getName());
            }
        }
        EventLog.getInstance().logEvent(new Event("Filtered LOC by Genre: " + newGenre));
        return n;
    }

    //EFFECTS: returns the List of contents by given language
    public List<String> contentbyLang(String newLang) {
        List<String> n = new ArrayList<>();
        for (Content c : loC) {

            if (newLang.equals(c.getLanguage())) {
                n.add(c.getName());
            }
        }
        EventLog.getInstance().logEvent(new Event("Filtered LOC by Language: " + newLang));
        return n;
    }

    //EFFECTS: returns the List of contents by given ratings
    public List<String> contentbyRatings(int newRatings) {
        List<String> n = new ArrayList<>();
        for (Content c : loC) {

            if (newRatings == c.getRatings()) {
                n.add(c.getName());
            }
        }
        EventLog.getInstance().logEvent(new Event("Filtered LOC by Ratings: " + newRatings));
        return n;
    }

    //EFFECTS: returns the List of contents by given site
    public List<String> contentbySite(String newSite) {
        List<String> n = new ArrayList<>();
        for (Content c : loC) {

            if (newSite.equals(c.getLocation())) {
                n.add(c.getName());
            }
        }
        EventLog.getInstance().logEvent(new Event("Filtered LOC by Location: " + newSite));
        return n;
    }

    //MODIFIES: this
    //EFFECTS: adds a content to the List of contents
    public void addToList(Content c) {
        if (!loC.contains(c)) {
            loC.add(c);
            EventLog.getInstance().logEvent(new Event("Added " + c.getName() + " to LOC"));
        }
    }

    //EFFECTS: returns the length of List of contents
    public int length() {
        return loC.size();
    }

    //EFFECTS: checks whether the List of contents is empty or not
    public boolean isEmpty() {
        return loC.size() == 0;
    }

    //EFFECTS: returns the next item in the List of contents
    public Content getNextContent() {
        Content ret = loC.get(position);
        position++;
        return ret;
    }

    //MODIFIES: this
    //EFFECTS: removes a content from the list of content
    public void removeContent(String name) {
        loC.removeIf(h -> h.getName().equals(name));
        EventLog.getInstance().logEvent(new Event("Removed " + name + " from LOC"));
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("loc", loCToJson());
        EventLog.getInstance().logEvent(new Event("Saved " + name));
        return json;
    }

    //EFFECTS: return contents in this list of Contents as a JSON array
    private JSONArray loCToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Content c : loC) {
            jsonArray.put(c.toJson());
        }
        return jsonArray;
    }
}
