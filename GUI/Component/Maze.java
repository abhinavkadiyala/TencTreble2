Public class Maze{
  protected Wall [] walls;
  
  public Maze (){
    walls = new Wall[];
  }
  
  public addWall(Wall wall){
    walls.add(wall);
  }

}
