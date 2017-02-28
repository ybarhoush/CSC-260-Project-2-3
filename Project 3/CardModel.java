/**
 * Created by David on 2/27/17.
 */
public class CardModel {
    private int num;
    private boolean isSelected;

    protected CardModel(int n){
        this.num = n;

    }

    public int getNum(){return this.num;}
    public boolean isSelected() {return this.isSelected;}

//    public void setNum(int n){this.num = n;}
    public void unSelect(){this.isSelected = !isSelected;}

}
