package com.fhce.egovf.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.fhce.egovf.dao.menuUsuarioDao;
import com.fhce.egovf.dao.usuarioDao;
import com.fhce.egovf.dto.passwordDtoRequest;
import com.fhce.egovf.dto.roleDto;
import com.fhce.egovf.dto.userDto;
import com.fhce.egovf.dto.usuarioDtoResponse;
import com.fhce.egovf.model.menuUsuarioModel;
import com.fhce.egovf.model.usuarioModel;
import com.fhce.egovf.service.usuarioService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class usuarioServiceImpl implements usuarioService{
	//private final UserRepository userRepository;
	private final usuarioDao usuarioDao;
	private final menuUsuarioDao menuUsuarioDao;
	private final ModelMapper modelMapper; 
	@Override
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String username) {
				userDto userDto = new userDto();
				List<usuarioModel>usuario = usuarioDao.getCorreo(username);
				if(usuario.size()>0) {
					userDto.setUsername(usuario.get(0).getCorreo());
					userDto.setPassword(usuario.get(0).getPass());
					userDto.setRoleDto(roleDto.USER);
				}
				return userDto;
				/*return userRepository.findByEmail(username)
						.orElseThrow(()-> new UsernameNotFoundException("User not Found"));*/
			}
		};
	}
	public List<usuarioDtoResponse> getListaUsuario(){
		List<usuarioDtoResponse>usuarios = this.usuarioDao.findAll().stream()
				.map(usuario -> this.modelMapper.map(usuario, usuarioDtoResponse.class))
				.collect(Collectors.toList());
		
		return usuarios;
	}
	public usuarioDtoResponse getUsuario(Long cif) {
		usuarioModel usuarioModel=this.usuarioDao.getUsuario(cif);
		return(this.modelMapper.map(usuarioModel, usuarioDtoResponse.class));
	}
	public usuarioDtoResponse updateUsuario(usuarioDtoResponse usuarioDtoResponse) {
		usuarioModel usuarioModel = this.modelMapper.map(usuarioDtoResponse, usuarioModel.class);
		
		//preguntamos su el usuario es empleado de tipo 2 para designarle en el menu el modulo SCC
		if(usuarioModel.getEmpleado() == 2) {
			// Buscamos en la tabla menuUsuario si ya exsite 
			menuUsuarioModel menuUsuarioModel = this.menuUsuarioDao.findCif(usuarioDtoResponse.getCif());
			if(menuUsuarioModel == null) {
				//si no existe creamos uno con el cif
				menuUsuarioModel = new menuUsuarioModel();
				menuUsuarioModel.setCif(usuarioModel.getCif());
			}
			menuUsuarioModel.setIdmenu((long)16);
			menuUsuarioModel.setEstado(1);
			this.menuUsuarioDao.save(menuUsuarioModel);
		}
		//
		usuarioModel.setEmpleado(1);
		this.usuarioDao.save(usuarioModel);
		
		return this.modelMapper.map(usuarioModel, usuarioDtoResponse.class);
	}
	public boolean updatePass(passwordDtoRequest passwordDtoRequest)throws Exception {
		usuarioModel usuario = this.usuarioDao.getUsuario(passwordDtoRequest.getCif());
		if(usuario.getPass().equals(pass(passwordDtoRequest.getPass1()))) {
			usuario.setPass(pass(passwordDtoRequest.getPass3()));
			this.usuarioDao.save(usuario);
			return (true);
		}
		else
			return(false);
	}
	public usuarioDtoResponse updatePassAdmin(passwordDtoRequest passwordDtoRequest )throws Exception {
		usuarioModel usuario = this.usuarioDao.getUsuario(passwordDtoRequest.getCif());
		usuario.setPass((pass(passwordDtoRequest.getCif().toString())));
		this.usuarioDao.save(usuario);
		return (this.modelMapper.map(usuario, usuarioDtoResponse.class));
	}
	public String pass(String password) throws Exception {
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] digest = md.digest(password.getBytes(StandardCharsets.UTF_8));
		StringBuilder hexString = new StringBuilder();
        for (byte b : digest) {
            hexString.append(String.format("%02x", b));
        }
		return hexString.toString();
	}

}
