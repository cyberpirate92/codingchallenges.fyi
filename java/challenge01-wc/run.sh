rm ../out/*.class
echo ""
echo "wc output"
echo "------------------"
wc ../../test_data/challenge01-wc/*
# wc ../../test_data/challenge01-wc/test1.txt
echo ""
echo "ccwc output"
echo "------------------"
javac ccwc.java -d ../out && java -cp ../out ccwc ../../test_data/challenge01-wc/*
# javac ccwc.java -d ../out && java -cp ../out ccwc ../../test_data/challenge01-wc/test1.txt
echo ""
echo "wc -m output"
echo "------------------"
wc -m ../../test_data/challenge01-wc/*
# wc -m ../../test_data/challenge01-wc/test1.txt
echo ""
echo "ccwc -m output"
echo "------------------"
javac ccwc.java -d ../out && java -cp ../out ccwc -m ../../test_data/challenge01-wc/*
# javac ccwc.java -d ../out && java -cp ../out ccwc -m ../../test_data/challenge01-wc/test1.txt
echo ""