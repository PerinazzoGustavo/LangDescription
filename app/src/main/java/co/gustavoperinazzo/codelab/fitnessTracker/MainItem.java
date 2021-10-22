package co.gustavoperinazzo.codelab.fitnessTracker;

public class  MainItem {
    private int id;
    private int drawableId;
    private int textStringId;
    private int itemBackground;

    public MainItem(int id, int drawableId, int textStringId, int background) {
        this.id = id;
        this.drawableId = drawableId;
        this.textStringId = textStringId;
        this.itemBackground = background;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public void setItemBackground(int itemBackground) {
        this.itemBackground = itemBackground;
    }

    public int getId() {
        return id;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public int getItemBackground() {
        return itemBackground;
    }

    public int getTextStringId() {
        return textStringId;
    }

    public void setTextStringId(int textStringId) {
        this.textStringId = textStringId;
    }
}
