import java.util.Random;

/**
 * Created by Lukas on 28.04.2016.
 */
public class Main {

    public static void main(String[] args) {
        new Generator(175,100);
    }
}

class Generator{
    private int[][] world;
    private int worldsize_x;
    private int worldsize_y;
    //genertation options
    private int number_Trees;
    private int number_randome_stone;


    public Generator(int x, int y){
        number_Trees = 10;
        number_randome_stone = 3;
        worldsize_x = x;
        worldsize_y = y;
        init_world();
        fill_stone();
        fill_dirt();
        fill_grass();
        for (int i = 1; i < number_randome_stone ; i++) {fill_stone_rand();}
        for (int i = 1; i < number_Trees; i++) {place_trees();}
        debug();

    }

    protected void init_world(){
        world = new int[worldsize_y][worldsize_x];

        for (int i = 0; i < worldsize_y; i++) {
            for (int j = 0; j < worldsize_x; j++) {
                world[i][j] = 0;
            }
        }
    }
    protected void fill_stone(){
        for (int y = worldsize_y-(worldsize_y/2); y < worldsize_y; y++) {
            for (int x = 0; x < worldsize_x; x++) {
                world[y][x] = 5;                        //stein noch implementieren
            }
        }
    }
    protected void fill_dirt(){
        int end_height = worldsize_y - (worldsize_y/4);
        int start_height = worldsize_y - (worldsize_y/2);

        for (int y = start_height; y <= end_height ; y++) {
            for (int x = 0; x < worldsize_x; x++) {
                world[y][x] = 4;
            }
        }
    }
    protected void fill_grass(){
        int end_height = worldsize_y - (worldsize_y/2);
        int start_height = worldsize_y - (worldsize_y/2);

        for (int y = start_height; y <= end_height ; y++) {
            for (int x = 0; x < worldsize_x; x++) {
                world[y][x] = 1;
            }
        }
    }
    protected void fill_stone_rand(){
        Random rand = new Random();
        int posy = rand.nextInt(worldsize_y/8) + worldsize_y/2; // worldsize/ 8 damit sie in der oberen hälfte plaziert werden
        int posx = rand.nextInt(worldsize_x);
        world[posy][posx] = 5;

        int end_height = worldsize_y - (worldsize_y/4);
        int start_height = worldsize_y - (worldsize_y/2);

        for (int y = start_height; y <= end_height ; y++) {
            for (int x = 1; x < worldsize_x-1; x++) {
                if(world[y][x] == 5){
                    int adersize = rand.nextInt(4)+1;

                    for (int wiederh = 0; wiederh < adersize; wiederh++) {
                        int richtung = rand.nextInt(7)+1;
                        switch (richtung){
                            case 1: world[y][x+1] = 5;
                                break;
                            case 2: world[y+1][x+1] = 5;
                                break;
                            case 3: world[y+1][x] = 5;
                                break;
                            case 4: world[y+1][x-1] = 5;
                                break;
                            case 5: world[y][x-1] = 5;
                                break;
                            case 6: world[y-1][x-1] = 5;
                                break;
                            case 7: world[y-1][x] = 5;
                                break;
                            case 8: world[y-1][x+1] = 5;
                                break;
                        }

                    }
                }
            }
        }
    }
    protected void place_trees(){
        int grasHeight = worldsize_y - (worldsize_y/2); // entspricht der endheight von fill_gras
        Random rand = new Random();
        int place_point_x = rand.nextInt(worldsize_x);
        for (int i = grasHeight-1; i > (grasHeight-10); i--) {
            world[i][place_point_x] = 9;
        }

    }
    protected void debug(){
        for (int i = 0; i < worldsize_y; i++) {
            for (int j = 0; j < worldsize_x ; j++) {
                System.out.print(world[i][j]);
            }
            System.out.print("\n");
        }
    }
}
