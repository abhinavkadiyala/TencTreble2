import java.util.Arrays;
import java.util.Collections;
import java.util.Random; 
/**
 * recursive backtracking algorithm
 * shamelessly borrowed from the ruby at
 * http://weblog.jamisbuck.org/2010/12/27/maze-generation-recursive-backtracking
 * <p/>
 * Stolen from <a href="http://rosettacode.org/wiki/Maze">here</a>.
 * Edited to allow multiple-path traversal and encoding by saving seed, dimensions, and density
 */
public class MazeGenerator {
    private final int x;        //default 8 //01-99
    private final int y;        //default 8 //01-99
    private final int[][] maze;
    private final int[][] visited;
    private final long seed;
    private Random rand;
    private final int density;  //default 7 //1-9
 
    public MazeGenerator(int x, int y) {
        density = 7;
        this.x = x;
        this.y = y;
        maze = new int[this.x][this.y];
        visited = new int[this.x][this.y];
        seed = System.currentTimeMillis();
        rand = new Random(seed);
        generateMaze(0, 0);
    }
    public MazeGenerator(int x, int y, long s) {
        density = 7;
        this.x = x;
        this.y = y;
        maze = new int[this.x][this.y];
        visited = new int[this.x][this.y];
        seed = s;
        rand = new Random(seed);
        generateMaze(0,0);
    }
    public MazeGenerator(int x, int y, long s, int ds) {
        density = ds;
        this.x = x;
        this.y = y;
        maze = new int[this.x][this.y];
        visited = new int[this.x][this.y];
        seed = s;
        rand = new Random(seed);
        generateMaze(0,0);
    }
 
    public void display() {
        System.out.println(x+" "+y+" "+density+" "+seed);
        for (int i = 0; i < y; i++) {
            // draw the north edge
            for (int j = 0; j < x; j++) {
                System.out.print((maze[j][i] & 1) == 0 ? "+---" : "+   ");
            }
            System.out.println("+");
            // draw the west edge
            for (int j = 0; j < x; j++) {
                System.out.print((maze[j][i] & 8) == 0 ? "|   " : "    ");
            }
            System.out.println("|");
        }
        // draw the bottom line
        for (int j = 0; j < x; j++) {
            System.out.print("+---");
        }
        System.out.println("+");
    }
 
    private void generateMaze(int cx, int cy) {
        Dir[] dirs = Dir.values();
        for (int i = 0; i < 4; i++) {
            int n = rand.nextInt(4);
            Dir t = dirs[i];
            dirs[i] = dirs[n];
            dirs[n] = t;
        }
        //Collections.shuffle(Arrays.asList(dirs));
        for (Dir dir : dirs) {
            int nx = cx + dir.dx;
            int ny = cy + dir.dy;
            if (between(nx, x) && between(ny, y)
                    && (visited[nx][ny] < 2)) {
                maze[cx][cy] |= dir.bit;
                maze[nx][ny] |= dir.opposite.bit;
                visited[nx][ny]+= (rand.nextInt(density)+3)/3;
                generateMaze(nx, ny);
            }
        }
    }
 
    private static boolean between(int v, int upper) {
        return (v >= 0) && (v < upper);
    }
 
    private enum Dir {
        N(1, 0, -1), S(2, 0, 1), E(4, 1, 0), W(8, -1, 0);
        private final int bit;
        private final int dx;
        private final int dy;
        private Dir opposite;
 
        // use the static initializer to resolve forward references
        static {
            N.opposite = S;
            S.opposite = N;
            E.opposite = W;
            W.opposite = E;
        }
 
        private Dir(int bit, int dx, int dy) {
            this.bit = bit;
            this.dx = dx;
            this.dy = dy;
        }
    };
 
    public static void main(String[] args) {
        int x = args.length >= 1 ? (Integer.parseInt(args[0])) : 8;
        int y = args.length == 2 ? (Integer.parseInt(args[1])) : 8;
        MazeGenerator maze = new MazeGenerator(x, y);
        maze.display();
    }
    public static void seed(int x, int y, long s) {
        MazeGenerator maze = new MazeGenerator(x,y,s);
        maze.display();
    }
    public static void seed(int x, int y, long s, int d) {
        MazeGenerator maze = new MazeGenerator(x,y,s,d);
        maze.display();
    }
    public static void encoded(long enc) {
        int x = (int)(enc % 100);
        enc /= 100;
        int y = (int)(enc % 100);
        enc /= 100;
        int d = (int)(enc % 10);
        enc /= 10;
        seed(x, y, enc, d);
    }
    
    public long encode() {
        return ((seed * 10 + density) * 100 + y) * 100 + x;
    }
 
}
