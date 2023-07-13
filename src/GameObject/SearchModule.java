package src.GameObject;



import java.util.List;

import javafx.scene.Node;
import javafx.scene.Parent;

import src.Scene.BattleSceneController;

public class SearchModule extends Module {
  // 搜索模式
  private String search_module;
  // 搜索范围
  protected double search_range;
  // 搜索强度
  private double search_intensity;
  // 是否锁定
  private boolean is_lock;
  // 锁定敌人
  private GameObject lo;
  // 依赖的游戏物体
  private GameObject o;

  public SearchModule(String nameString, String introduceString, String search_moduleString, double search_range,
      double search_intensity, GameObject o) {
    name = nameString;
    type = "SearchModule";
    introduce = introduceString;
    search_module = search_moduleString;
    this.search_range = search_range;
    this.search_intensity = search_intensity;
    this.o = o;
  }

  public GameObject search(List<GameObject> l, String searchName, double search_range) {

    for (GameObject item : l) {
      if (item.getId() == searchName) {
        if (o.getDistance(item) < search_range) {
          return item;
        }

      }
    }
    return null;
  }

  public GameObject lockOn(List<GameObject> l,String searchName, double search_range) {

    GameObject eo = (GameObject) this.search(l, searchName, search_range);
    if (eo != null) {
      double d = o.getDistance(eo);
      if (d <= search_range) {
        if (eo.RCS > 0) {
          return eo;
        }

      }
    }

    return null;
  }

  public GameObject lockOn(List<GameObject> l,String searchName) {
    return lockOn(l,searchName, this.search_range);
  }

  // 定义一个递归方法，参数为要查找的fx:id或者id值，以及要遍历的根节点
  public Node findNodeById(String id, Parent root) {
    // 如果根节点的fx:id或者id与要查找的值相同，返回根节点
    if (id.equals(root.getId()) || id.equals(root.getId())) {
      return root;
    }
    // 否则，遍历根节点的所有子节点，并递归调用该方法
    for (Node child : root.getChildrenUnmodifiable()) {
      // 如果子节点是一个容器类型的节点，继续递归
      if (child instanceof Parent) {
        Node found = findNodeById(id, (Parent) child);
        // 如果找到了匹配的节点，返回该节点
        if (found != null) {
          return found;
        }
      }
      // 否则，检查子节点的fx:id或者id是否与要查找的值相同，如果是，返回该节点
      else {
        if (id.equals(child.getId()) || id.equals(child.getId())) {
          return child;
        }
      }
    }
    // 如果遍历完所有子节点都没有找到匹配的节点，返回null
    return null;
  }

}
