package cn.xy.algorithm.BinaryTree.rbt;

import java.util.ArrayList;

/**
 * @author xiangyu
 * @date 2023-02-21 21:48
 */
public class RBTTree {
    // TODO: 等待调试
    Indexes head;

    public static void main(String[] args) {
        ArrayList<Data> arrayList = new ArrayList<Data>();
        arrayList.add(new Data(16));
        arrayList.add(new Data(24));
        arrayList.add(new Data(12));
        arrayList.add(new Data(32));
        arrayList.add(new Data(14));
        arrayList.add(new Data(26));
        arrayList.add(new Data(34));
        arrayList.add(new Data(10));
        arrayList.add(new Data(8));
        arrayList.add(new Data(28));
        arrayList.add(new Data(38));
        arrayList.add(new Data(20));
        RBTTree bTree = new RBTTree();
        for (Data data : arrayList) {
            bTree.add(data);
        }
        bTree.show();
    }

    public void show() {
        Indexes replace = head;
        if (head == null) {
            System.out.println("空表");
        } else {
            showDemo(replace);
        }
    }

    public void showDemo(Indexes indexes) {
        System.out.print(indexes.data1.data);
        if (indexes.data2 != null) {
            System.out.println(indexes.data2.data);
        } else {
            System.out.println();
        }

        if (indexes.left != null) {
            System.out.println("要向左走");
            showDemo(indexes.left);
        }
        if (indexes.middle != null) {
            System.out.println("要向中间走");
            showDemo(indexes.middle);
        }
        if (indexes.right != null) {
            System.out.println("要向右走");
            showDemo(indexes.right);
        }
    }

    public void add(Data data) {
        Indexes replace = head;
        if (head == null) {
            head = new Indexes(data);
        } else {
            addDemo(replace, data);
        }
    }

    public void addDemo(Indexes indexes, Data data) {
        if (data.data < indexes.data1.data && indexes.data2 == null) {//子树小于第一个点，并且第二个数为空
            if (indexes.left == null) {//无子树
                indexes.data2 = indexes.data1;
                indexes.data1 = data;
            } else if (indexes.left.left == null) {//有子树且子树无子树
                if (indexes.left.data2 == null) {//左子树无第二个数
                    if (data.data > indexes.left.data1.data) {//参数值大于左子树data1
                        indexes.left.data2 = data;
                    } else {
                        indexes.left.data2 = indexes.left.data1;
                        indexes.left.data1 = data;
                    }
                } else if (data.data < indexes.left.data1.data) {//参数值小于左子树第一个点并且有左子树第二个点
                    indexes.data2 = indexes.data1;
                    indexes.data1 = indexes.left.data1;
                    indexes.middle = new Indexes(indexes.left.data2);
                    indexes.left.data1 = data;
                    indexes.left.data2 = null;
                } else if (data.data > indexes.left.data1.data && data.data < indexes.left.data2.data) {//参数处于左子树第一个值和第二个值之间
                    indexes.middle = new Indexes(indexes.left.data2);
                    indexes.data2 = indexes.data1;
                    indexes.data1 = data;
                    indexes.left.data2 = null;
                } else if (data.data > indexes.left.data1.data) {//参数大于左子树第二个值
                    indexes.middle = new Indexes(data);
                    indexes.data2 = indexes.data1;
                    indexes.data1 = indexes.left.data2;
                    indexes.left.data2 = null;
                }
            } else {
                addDemo(indexes.left, data);
            }
        } else if (data.data > indexes.data1.data && indexes.data2 == null) {//data大于indexes第一个点,并且无第二个点
            //无子树情况
            if (indexes.right == null) {
                indexes.data2 = data;
            } else if (indexes.right.right == null) {//有子树情况
                if (indexes.right.data2 == null) {//右子树没有第二个值
                    if (data.data < indexes.right.data1.data) {
                        indexes.right.data2 = indexes.right.data1;
                        indexes.right.data1 = data;
                    } else {
                        indexes.right.data2 = data;
                    }
                } else if (data.data > indexes.right.data2.data) {//参数值大于右子树第二个值
                    indexes.middle = new Indexes(indexes.right.data1);
                    indexes.data2 = indexes.right.data2;
                    indexes.right.data2 = null;
                    indexes.right.data1 = data;
                } else if (data.data > indexes.right.data1.data && data.data < indexes.right.data2.data) {//参数处于右子树第一个值和第二个值之间
                    indexes.middle = new Indexes(indexes.right.data1);
                    indexes.data2 = data;
                    indexes.right.data1 = indexes.right.data2;
                    indexes.right.data2 = null;
                } else if (data.data < indexes.right.data1.data) {//参数小于右子树第一个值
                    indexes.middle = new Indexes(data);
                    indexes.data2 = indexes.right.data1;
                    indexes.right.data1 = indexes.right.data2;
                    indexes.right.data2 = null;
                }
            } else {
                addDemo(indexes.right, data);
            }
        } else if (data.data < indexes.data1.data) {//参数小于data1 并且有data2
            if (indexes.left == null) {//无子树
                indexes.right = new Indexes(indexes.data2);
                indexes.data2 = null;
                indexes.left = new Indexes(data);
            } else if (indexes.left.left == null) {//子树无子树

                if (indexes.left.data2 == null) {//左子树无第二个值
                    if (data.data < indexes.left.data1.data) {
                        indexes.left.data2 = indexes.left.data1;
                        indexes.left.data1 = data;
                    } else {
                        indexes.left.data2 = data;
                    }
                } else {
                    if (data.data < indexes.left.data1.data) {//参数值小于左子树第一个值
                        indexes.left.left = new Indexes(data);
                        indexes.left.right = new Indexes(indexes.left.data2);
                        indexes.left.data2 = null;
                        Indexes newIndexes3 = new Indexes(indexes.data2);
                        newIndexes3.right = indexes.right;
                        newIndexes3.left = indexes.middle;
                        indexes.right = newIndexes3;
                        indexes.middle = null;
                        indexes.data2 = null;
                    } else if (data.data > indexes.left.data1.data && data.data < indexes.left.data2.data) {//参数值处在中间
                        indexes.left.left = new Indexes(indexes.left.data1);
                        indexes.left.right = new Indexes(indexes.data2);
                        indexes.left.data1 = data;
                        Indexes newIndexes3 = new Indexes(indexes.data2);
                        newIndexes3.right = indexes.right;
                        newIndexes3.left = indexes.middle;
                        indexes.right = newIndexes3;
                        indexes.middle = null;
                        indexes.data2 = null;
                    } else if (data.data > indexes.left.data2.data) {//参数值大于第二个点
                        indexes.left.left = new Indexes(indexes.left.data1);
                        indexes.left.right = new Indexes(data);
                        indexes.left.data1 = indexes.left.data2;
                        Indexes newIndexes3 = new Indexes(indexes.data2);
                        newIndexes3.right = indexes.right;
                        newIndexes3.left = indexes.middle;
                        indexes.right = newIndexes3;
                        indexes.middle = null;
                        indexes.data2 = null;
                    }
                }
            } else {
                addDemo(indexes.left, data);
            }
        } else if (data.data > indexes.data1.data && data.data < indexes.data2.data) {//参数值介于data1和data2之间
            if (indexes.middle == null) {//无子树情况
                indexes.right = new Indexes(indexes.data2);
                indexes.data2 = null;
                indexes.right = new Indexes(indexes.data1);
                indexes.data1 = data;
            } else if (indexes.middle.left == null) {

                if (indexes.middle.data2 == null) {
                    if (data.data < indexes.middle.data1.data) {
                        indexes.middle.data2 = indexes.middle.data1;
                        indexes.middle.data1 = data;
                    } else {
                        indexes.middle.data2 = data;
                    }
                } else {
                    if (data.data < indexes.middle.data1.data) {//小于中间data1
                        //左侧
                        Indexes indexes1 = new Indexes(indexes.left.data1);
                        if (indexes.left.data2 != null) {
                            indexes1.data2 = indexes.left.data2;
                        }
                        indexes.left.left = indexes1;

                        indexes.left.right = new Indexes(data);

                        indexes.left.data1 = indexes.data1;
                        indexes.left.data2 = null;
                        //右侧
                        Indexes indexes3 = new Indexes(indexes.right.data1);
                        if (indexes.right.data2 != null) {
                            indexes3.data2 = indexes.right.data2;
                        }
                        indexes.right.right = indexes3;

                        indexes.right.left = new Indexes(indexes.middle.data2);

                        indexes.right.data1 = indexes1.data2;
                        indexes.right.data2 = null;
                        //中间
                        indexes.data1 = indexes.middle.data1;
                        indexes.data2 = null;
                        indexes.middle = null;
                    } else if (data.data > indexes.middle.data1.data && data.data < indexes.middle.data2.data) {
                        Indexes indexes1 = new Indexes(indexes.left.data1);
                        if (indexes.left.data2 != null) {
                            indexes1.data2 = indexes.left.data2;
                        }
                        indexes.left.left = indexes1;

                        indexes.left.right = new Indexes(indexes.middle.data1);

                        indexes.left.data1 = indexes.data1;
                        indexes.left.data2 = null;
                        //右侧
                        Indexes indexes3 = new Indexes(indexes.right.data1);
                        if (indexes.right.data2 != null) {
                            indexes3.data2 = indexes.right.data2;
                        }
                        indexes.right.right = indexes3;

                        indexes.right.left = new Indexes(indexes.middle.data2);

                        indexes.right.data1 = indexes1.data2;
                        indexes.right.data2 = null;
                        //中间
                        indexes.data1 = data;
                        indexes.data2 = null;
                        indexes.middle = null;
                    } else if (data.data > indexes.middle.data2.data) {
                        //左侧
                        Indexes indexes1 = new Indexes(indexes.left.data1);
                        if (indexes.left.data2 != null) {
                            indexes1.data2 = indexes.left.data2;
                        }
                        indexes.left.left = indexes1;

                        indexes.left.right = new Indexes(indexes.middle.data1);

                        indexes.left.data1 = indexes.data1;
                        indexes.left.data2 = null;
                        //右侧
                        Indexes indexes3 = new Indexes(indexes.right.data1);
                        if (indexes.right.data2 != null) {
                            indexes3.data2 = indexes.right.data2;
                        }
                        indexes.right.right = indexes3;

                        indexes.right.left = new Indexes(data);

                        indexes.right.data1 = indexes1.data2;
                        indexes.right.data2 = null;
                        //中间
                        indexes.data1 = indexes.middle.data2;
                        indexes.data2 = null;
                        indexes.middle = null;
                    }
                }
            } else {
                addDemo(indexes.middle, data);
            }
        } else if (data.data > indexes.data2.data) {//大于data2
            if (indexes.right == null) {//无子树
                indexes.right = new Indexes(data);
                indexes.right = new Indexes(indexes.data1);
                indexes.data1 = indexes.data2;
                indexes.data2 = null;
            } else if (indexes.right.right == null) {//子树无子树

                if (indexes.right.data2 == null) {//右子树无第二个值
                    if (data.data < indexes.right.data1.data) {//参数值小于右子树data1
                        indexes.right.data2 = indexes.right.data1;
                        indexes.right.data1 = data;
                    } else {
                        indexes.right.data2 = data;
                    }
                } else {
                    if (data.data < indexes.right.data1.data) {//参数值小于左子树第一个值
                        indexes.right.right = new Indexes(indexes.right.data2);
                        indexes.right.data2 = null;

                        indexes.right.left = new Indexes(data);

                        Indexes newIndexes3 = new Indexes(indexes.left.data1);
                        if (indexes.left.data2 != null) {
                            newIndexes3.data2 = indexes.left.data2;
                        }
                        indexes.left.left = newIndexes3;
                        Indexes newIndexes4 = new Indexes(indexes.middle.data1);
                        if (indexes.middle.data2 != null) {
                            newIndexes4.data2 = indexes.middle.data2;
                        }
                        indexes.left.right = newIndexes4;
                        indexes.left.data1 = indexes.data1;
                        indexes.left.data2 = null;

                        indexes.data1 = indexes.data2;
                        indexes.data2 = null;
                        indexes.middle = null;
                    } else if (data.data > indexes.right.data1.data && data.data < indexes.right.data2.data) {//参数值处在中间
                        indexes.right.right = new Indexes(indexes.right.data2);

                        indexes.right.left = new Indexes(indexes.right.data1);

                        indexes.right.data1 = data;
                        indexes.right.data2 = null;

                        Indexes newIndexes3 = new Indexes(indexes.left.data1);
                        if (indexes.left.data2 != null) {
                            newIndexes3.data2 = indexes.left.data2;
                        }
                        indexes.left.left = newIndexes3;
                        Indexes newIndexes4 = new Indexes(indexes.middle.data1);
                        if (indexes.middle.data2 != null) {
                            newIndexes4.data2 = indexes.middle.data2;
                        }
                        indexes.left.right = newIndexes4;
                        indexes.left.data1 = indexes.data1;
                        indexes.left.data2 = null;

                        indexes.data1 = indexes.data2;
                        indexes.data2 = null;
                        indexes.middle = null;
                    } else if (data.data > indexes.right.data2.data) {//参数值大于第二个点
                        indexes.right.right = new Indexes(data);

                        indexes.right.left = new Indexes(indexes.right.data1);

                        indexes.right.data1 = indexes.right.data2;
                        indexes.right.data2 = null;

                        Indexes newIndexes3 = new Indexes(indexes.left.data1);
                        if (indexes.left.data2 != null) {
                            newIndexes3.data2 = indexes.left.data2;
                        }
                        indexes.left.left = newIndexes3;
                        Indexes newIndexes4 = new Indexes(indexes.middle.data1);
                        if (indexes.middle.data2 != null) {
                            newIndexes4.data2 = indexes.middle.data2;
                        }
                        indexes.left.right = newIndexes4;
                        indexes.left.data1 = indexes.data1;
                        indexes.left.data2 = null;

                        indexes.data1 = indexes.data2;
                        indexes.data2 = null;
                        indexes.middle = null;
                    }
                }
            } else {
                addDemo(indexes.right, data);
            }
        }
    }
    public boolean judgeSubTree(Indexes indexes) {
        return indexes.left != null && indexes.right != null;
    }
}

class Indexes {
    Data data1, data2;
    Indexes left, right, middle;

    Indexes(Data data) {
        this.data1 = data;
    }
}

class Data {
    int data;

    Data(int data) {
        this.data = data;
    }
}
