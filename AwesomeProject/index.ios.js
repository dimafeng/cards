'use strict';

var React = require('react-native');
var SideMenu = require('react-native-side-menu');
var window = require('Dimensions').get('window');

var {
    AppRegistry,
    StyleSheet,
    Text,
    View,
    ListView
    } = React;

var Menu = React.createClass({
    render: function () {
        return (
            <View style={styles.menu}>
                <Text style={styles.caption}>Menu caption</Text>
                <Text style={styles.item}>About</Text>
                <Text style={styles.item}>Contacts</Text>
            </View>
        );
    }
});

var List = React.createClass({
        getInitialState: function() {
            var ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2});
            return {
                dataSource: ds.cloneWithRows(['row 1', 'row 2']),
            };
        },
        render: function () {
            return (<ListView
                dataSource={this.state.dataSource}
                renderRow={(rowData) => <Text>{rowData}</Text>}
                />
            );
        }
    }
);

var simple = React.createClass({
    render: function () {
        return (
            <SideMenu menu={<Menu />}>
                <View style={styles.container}>
                    <List />
                </View>
            </SideMenu>
        );
    }
});

var styles = StyleSheet.create({
    menu: {
        flex: 1,
        width: window.width,
        height: window.height,
        backgroundColor: 'gray',
        justifyContent: 'center',
        padding: 20
    },
    caption: {
        fontSize: 20,
        fontWeight: 'bold',
        alignItems: 'center',
    },
    item: {
        fontSize: 18,
        fontWeight: '300',
        paddingTop: 5,
    },
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: '#F5FCFF',
    },
    welcome: {
        fontSize: 20,
        textAlign: 'center',
        margin: 10,
    },
    instructions: {
        textAlign: 'center',
        color: '#333333',
        marginBottom: 5,
    },
});

AppRegistry.registerComponent('AwesomeProject', () => simple);