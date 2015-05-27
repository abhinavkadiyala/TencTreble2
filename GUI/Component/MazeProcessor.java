public class MazeProcessor
{
  
  private Maze myMaze;
  
  public MazeProcessor(Maze a)
  {
    myMaze = a;
  }
  
  public Maze generate(String maze)
  {
    int col = 0;
    
    for(int i = 0; i < maze.length(); i++)
    {
      if(maze.charAt(i) == '-')
      {
        putWallAt(i,col);
      }
      
      if(maze.charAt(i) == '\n')
      {
        ++col;
      }
    }
  }
  
  public putWallAt(int row, int col)
  {
    
  }
}
