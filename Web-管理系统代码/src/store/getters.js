const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  roles: state => state.user.roles,
  comId: state => state.user.comId,
  addRoutes: state => state.permission.addRoutes,
  permission_routes: state => state.permission.routes,
}
export default getters
