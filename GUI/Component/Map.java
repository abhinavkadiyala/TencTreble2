Public class Map{
  protected Wall [] walls;
  
  public Map (){
    walls = new Wall[];
  }
  
  public addWall(Wall wall){
    walls.add(wall);
  }

}
